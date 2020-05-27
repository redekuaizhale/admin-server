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
package com.redekuaizhale.dept.repository;

import com.redekuaizhale.base.repository.BaseRepository;
import com.redekuaizhale.dept.entity.DeptEntity;
import org.springframework.stereotype.Repository;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Repository
public interface DeptRepository extends BaseRepository<DeptEntity, String> {
}
