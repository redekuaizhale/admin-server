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
package com.redekuaizhale.utils.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.addDays;

/**
 * 日期工具类
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Slf4j
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * string 转 date
     *
     * @param dateStr
     * @return
     * @throws ServiceException
     */
    public static Date stringToDate(String dateStr) throws ServiceException {
        return stringToDate(dateStr, yyyy_MM_dd_HH_mm_ss);
    }

    public static Date stringToDate(String dateStr, String pattern) throws ServiceException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            if (StringUtils.isNotEmpty(dateStr)) {
                date = simpleDateFormat.parse(dateStr);
            }
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * date 转 string
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        return dateToString(date, yyyy_MM_dd_HH_mm_ss);
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String result = "";
        try {
            if (date != null) {
                result = simpleDateFormat.format(date);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @param date
     * @param day  想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @return
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 日期差天数、小时、分钟、秒数组
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long[] getDisTime(Date startDate, Date endDate) {
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};
    }

    /**
     * 日期差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDisDay(Date startDate, Date endDate) {
        long[] dis = getDisTime(startDate, endDate);
        long day = dis[0];
        if (dis[1] > 0 || dis[2] > 0 || dis[3] > 0) {
            day += 1;
        }
        return day;
    }

    /**
     * 日期差文字描述
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDisTimeStr(Date startDate, Date endDate) {
        long[] dis = getDisTime(startDate, endDate);
        return new StringBuilder().append(dis[0]).append("天").append(dis[1]).append("小时").append(dis[2]).append("分钟")
                .append(dis[3]).append("秒").toString();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }


    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }


    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取区间段日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getIntervalDate(Date startDate, Date endDate) {
        List<Date> dateArr = new ArrayList<>();
        long day = getDisDay(startDate, endDate);
        for (int i = 0; i < day - 1; i++) {
            dateArr.add(addDays(startDate, i + 1));
        }
        return dateArr;
    }
}
