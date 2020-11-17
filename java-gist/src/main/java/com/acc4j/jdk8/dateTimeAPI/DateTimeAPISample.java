package com.acc4j.jdk8.dateTimeAPI;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Java的Date，Calendar设计不足，在JDK8中提供了新版本API处理日期时间时区，换算，格式化等内容
 */
public class DateTimeAPISample {

    /**
     * Instant：表示时刻，不直接对应年月日信息，需要通过时区转换
     */
    public void instant() {
        System.out.println("--------------- Instant --------------");
        Instant i1 = Instant.now();
        // Instant与Date根据毫秒数相互转换
        Date d1 = new Date(i1.toEpochMilli());
        Instant i2 = Instant.ofEpochMilli(System.currentTimeMillis());
        // GMT+8（0时区起偏移8小时）
        ZonedDateTime z1 = i2.atZone(ZoneId.of("GMT+08:00"));
        OffsetDateTime o1 = i2.atOffset(ZoneOffset.ofHours(8));

        System.out.println("根据纪元时间（毫秒数）相互转换：" + i1.toEpochMilli() + " - " + d1.getTime());
        System.out.println(o1);
        System.out.println(z1);
        System.out.println();
    }

    /**
     * LocalDateTime：表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
     * LocalDate：表示与时区无关的日期，与LocalDateTime相比，只有日期信息，没有时间信息
     * LocalTime：表示与时区无关的时间，与LocalDateTime相比，只有时间信息，没有日期信息
     */
    public void localDateTime() {
        System.out.println("--------------- Local Date Time --------------");
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(2020, 11, 11, 11, 11, 11);
        LocalDateTime ldt3 = LocalDateTime.ofEpochSecond(Instant.now().getEpochSecond(), 111, ZoneOffset.ofHours(8));

        // 其他构造方式类比LocalDateTime
        LocalDate ld1 = LocalDate.now();
        LocalTime lt1 = LocalTime.now();
        // 相互转换，
        LocalDateTime ld4 = LocalDateTime.of(ld1, lt1);
        ld4 = ld1.atTime(lt1);
        ld4 = lt1.atDate(ld1);
        ld1 = ld4.toLocalDate();
        lt1 = ld4.toLocalTime();
        // 修改日期时间：1. 直接设置 2. 相对计算
        LocalDateTime ldt5 = ldt1.with(ChronoField.MILLI_OF_DAY, 0);
        LocalDateTime ldt6 = ldt1.withDayOfMonth(1);
        LocalDateTime ldt7 = ldt1.plusHours(1);
        LocalDateTime ldt8 = ldt1.plusWeeks(1);
        LocalDateTime ldt9 = ld1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).atTime(10, 0);

        System.out.println(ldt1 + " - " + ldt2 + " - " + ldt3);
        System.out.println(ld4 + " ： " + ld1 + " ： " + lt1);
        System.out.println("当前时间：" + ldt1);
        System.out.println("当前时间，设置为当天0时：" + ldt5);
        System.out.println("当前时间，日期设置为本月第一天：" + ldt6);
        System.out.println("当前时间，增加一小时：" + ldt7);
        System.out.println("当前时间，增加一周：" + ldt8);
        System.out.println("当前日期：" + ld1);
        System.out.println("当前日期的下一周10点：" + ldt9);
        System.out.println();
    }

    /**
     * DateTimeFormatter：线程安全的日期时间格式化
     */
    public void dateTimeFormatter() {
        System.out.println("--------------- DateTimeFormatter --------------");
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s1 = df1.format(LocalDateTime.of(2020, 11, 11, 11, 11, 11));
        String s2 = LocalDateTime.now().format(df1);
        LocalDateTime ldt1 = LocalDateTime.parse(s1, df1);

        System.out.println(s1 + " - " + s2);
        System.out.println(ldt1);
        System.out.println();
    }

    /**
     * Period：表示日期之间的差，用年月日表示，不能表示时间
     * Duration：表示时间差，用时分秒表等表示，也可以用天表示，一天严格等于24小时，不能用年月表示
     */
    public void periodAndDuration() {
        System.out.println("--------------- Period Duration --------------");
        LocalDate ld1 = LocalDate.of(1995, 1, 29);
        LocalDate ld2 = LocalDate.now();
        LocalTime lt1 = LocalTime.of(0, 0);
        LocalTime lt2 = LocalTime.now();
        Period p1 = Period.between(ld1, ld2);
        Duration d1 = Duration.between(lt1, lt2);

        System.out.println("日期差：" + p1.getYears() + "年 " + p1.getMonths() + "个月 " + p1.getDays() + "天");
        System.out.println("时间差：" + d1.toDays() + "天，或 " + d1.toHours() + "小时，或 " + d1.toMinutes() + "分钟，或 " + d1.getSeconds() + "秒");
        System.out.println();
    }

    /**
     * ZonedDateTime：表示特定时区的日期和时间
     * OffsetDateTime：表示相对偏移时区的日期和时间
     * ZoneId / ZoneOffset：表示时区 / 时区偏移量
     */
    public void zonedDateTime() {
        System.out.println("--------------- ZonedDateTime OffsetDateTime --------------");
        // 默认本地时区，ZoneId可以使用GMT形式，UTC形式，州/城市 名的形式
        ZonedDateTime zdt1 = ZonedDateTime.now(ZoneId.of("GMT+08:00"));
        OffsetDateTime odt1 = Instant.now().atOffset(ZoneOffset.ofHours(8));

        System.out.println(zdt1);
        System.out.println(odt1);
        System.out.println();
    }
}
