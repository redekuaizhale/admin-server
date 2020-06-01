/**
 * Copyright Dingxuan. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redekuaizhale.base.service;

import com.redekuaizhale.base.entity.BaseEntity;
import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.param.QueryParam;
import com.redekuaizhale.base.repository.BaseRepository;
import com.redekuaizhale.base.request.RequestPage;
import com.redekuaizhale.base.response.ResponsePage;
import com.redekuaizhale.constants.BaseEntityConstant;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.utils.threadlocal.UserThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 基础service
 *
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Slf4j
public abstract class BaseService<T extends BaseEntity> {

    public BaseRepository<T, String> baseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz = null;

    {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type claz = pType.getActualTypeArguments()[0];
            if (claz instanceof Class) {
                this.clazz = (Class<T>) claz;
            }
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public T findById(String id) {
        Optional<T> optional = baseRepository.findById(id);
        return optional.get();
    }

    /**
     * 保存或更新
     *
     * @param entity
     * @return
     */
    public T save(T entity) {
        UserEntity user = UserThreadLocalUtils.get();
        Date date = new Date();
        entity.setCreateDate(date);
        entity.setDelFlag(BaseEntityConstant.ENABLE.getValue());
        entity.setCreateUserId(user.getId());
        entity.setCreateDate(new Date());
        entity.setUpdateDate(entity.getCreateDate());
        entity.setUpdateUserId(entity.getCreateUserId());
        return baseRepository.save(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    public T update(T entity) {
        entity.setUpdateDate(new Date());
        UserEntity user = UserThreadLocalUtils.get();
        entity.setUpdateDate(new Date());
        entity.setUpdateUserId(user.getId());
        return baseRepository.save(entity);
    }

    /**
     * 逻辑删除
     *
     * @param id
     */
    public void deleteById(String id) {
        UserEntity user = UserThreadLocalUtils.get();
        T t = findById(id);
        t.setDelFlag(BaseEntityConstant.DELETE.getValue());
        t.setUpdateDate(new Date());
        t.setUpdateUserId(user.getId());
        baseRepository.save(t);
    }

    /**
     * 逻辑删除所有
     *
     * @param list
     */
    public void deleteAll(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (T t : list) {
            deleteById(t.getId());
        }
    }


    /**
     * 查询所有未逻辑删除数据
     *
     * @return
     */
    public List<T> findAll() {
        return baseRepository.findAllByDelFlag(BaseEntityConstant.ENABLE.getValue());
    }

    /**
     * 根据单字段名查询，返回一条数据
     *
     * @param field
     * @param value
     * @param orderParams
     * @return
     */
    public T findByProperty(String field, String value, OrderParam... orderParams) {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put(field, value);
        fieldMap.put("delFlag", BaseEntityConstant.ENABLE.getValue());
        List<T> list = findAllByProperties(fieldMap, orderParams);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据单字段名查询，返回多条数据
     *
     * @param field
     * @param value
     * @param orderParams
     * @return
     */
    public List<T> findAllByProperty(String field, String value, OrderParam... orderParams) {
        Map<String, Object> fieldMap = new HashMap<>(2);
        fieldMap.put("delFlag", BaseEntityConstant.ENABLE.getValue());
        fieldMap.put(field, value);
        List<T> list = findAllByProperties(fieldMap, orderParams);
        return CollectionUtils.isEmpty(list) ? null : list;
    }

    /**
     * 根据多个字段查询，返回单条数据
     *
     * @param properties
     * @param orderParams
     * @return
     */
    public T findByProperties(Map<String, Object> properties, OrderParam... orderParams) {
        RequestPage requestPage = new RequestPage();
        requestPage.setTotal(Integer.MAX_VALUE);
        properties.put("delFlag", BaseEntityConstant.ENABLE.getValue());

        List<QueryParam> queryParams = QueryParam.newQueryParams(properties);

        if (CollectionUtils.isEmpty(requestPage.getQueryParamList())) {
            requestPage.setQueryParamList(queryParams);
        } else {
            requestPage.getQueryParamList().addAll(queryParams);
        }

        List<OrderParam> orderParamList = OrderParam.newOrderParams(orderParams);

        if (CollectionUtils.isEmpty(requestPage.getOrderParamList())) {
            requestPage.setOrderParamList(orderParamList);
        } else {
            requestPage.getOrderParamList().addAll(orderParamList);
        }

        List<T> resultList = (List<T>) query(requestPage).getResultList();
        return CollectionUtils.isEmpty(resultList) ? null : resultList.get(0);
    }

    /**
     * 根据多个字段查询，返回多条数据
     *
     * @param properties
     * @param orderParams
     * @return
     */
    public List<T> findAllByProperties(Map<String, Object> properties, OrderParam... orderParams) {
        RequestPage requestPage = new RequestPage();
        requestPage.setTotal(Integer.MAX_VALUE);
        properties.put("delFlag", BaseEntityConstant.ENABLE.getValue());
        List<QueryParam> queryParams = QueryParam.newQueryParams(properties);
        if (CollectionUtils.isEmpty(requestPage.getQueryParamList())) {
            requestPage.setQueryParamList(queryParams);
        } else {
            requestPage.getQueryParamList().addAll(queryParams);
        }
        List<OrderParam> orderParamList = OrderParam.newOrderParams(orderParams);
        if (CollectionUtils.isEmpty(requestPage.getOrderParamList())) {
            requestPage.setOrderParamList(orderParamList);
        } else {
            requestPage.getOrderParamList().addAll(orderParamList);
        }
        return (List<T>) query(requestPage).getResultList();
    }

    public ResponsePage query(RequestPage requestPage) {
        List<QueryParam> queryParams = requestPage.getQueryParamList();
        Integer rowsTotal = countByQueryParams(queryParams);

        ResponsePage responsePage = new ResponsePage();
        responsePage.setTotal(rowsTotal);

        String hql = createHql(requestPage.getQueryParamList()) + createOrderHql(requestPage.getOrderParamList());
        log.debug("hql: " + hql);

        Query query = entityManager.createQuery(hql);
        QueryParam.setQueryParams(query, queryParams);

        query.setFirstResult(responsePage.getPageNum() * responsePage.getPageSize());
        query.setMaxResults(responsePage.getPageSize());

        List resultList = query.getResultList();
        responsePage.setResultList(resultList);

        return responsePage;
    }

    public void addQueryEnableFlag(RequestPage requestPage, List<String> attributes) {
        attributes.forEach(item -> {
            requestPage.getQueryParamList().add(QueryParam.newQueryParam(item + ".enabled", "=", BaseEntityConstant.ENABLE.getValue()));
        });
    }

    String createOrderHql(List<OrderParam> orderParams) {
        if (CollectionUtils.isEmpty(orderParams)) {
            return "";
        }
        String orderHql = " order by ";
        List<String> list = new ArrayList<>();
        for (OrderParam orderParam : orderParams) {
            list.add(" t." + orderParam.getProperty() + " " + orderParam.getDirection());
        }
        orderHql = orderHql + StringUtils.join(list.iterator(), ",");
        return orderHql;
    }

    Integer countByQueryParams(List<QueryParam> queryParams) {
        String hql = "select count(*) " + createHql(queryParams);
        log.debug("hql: " + hql);
        Query countQuery = entityManager.createQuery(hql);
        QueryParam.setQueryParams(countQuery, queryParams);
        Long rowsTotal = (Long) countQuery.getSingleResult();
        return rowsTotal.intValue();
    }

    String createHql(List<QueryParam> queryParams) {
        StringBuilder hql = new StringBuilder(" from " + this.clazz.getName() + " t where t.delFlag = " + BaseEntityConstant.ENABLE.getValue());
        return createHqldetail(queryParams, hql);
    }

    String createHqldetail(List<QueryParam> queryParams, StringBuilder hql) {
        for (QueryParam queryParam : queryParams) {
            String paramName = queryParam.getFieldName();
            String replace = paramName.replace(".", "");
            String operate = queryParam.getOperate();
            if (StringUtils.equalsIgnoreCase(operate, "StartWith") || StringUtils.equalsIgnoreCase(operate, "EndWith")) {
                operate = "like";
            }
            if (StringUtils.equalsIgnoreCase(operate, "between")) {
                hql.append(" and t." + paramName + " >= " + ":" + replace + "1");
                hql.append(" and t." + paramName + " <= " + ":" + replace + "2");
            } else {
                hql.append(" and t." + paramName + " " + operate + " " + ":" + replace);
            }
        }
        return hql.toString();
    }
}
