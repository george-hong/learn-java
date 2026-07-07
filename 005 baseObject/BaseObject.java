/**
 * 打印月历
 */

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BaseObject {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        // 获取在本月的第几天
        int dayOfMonth = date.getDayOfMonth();
        // 将日期更新到本月第一天
        date = date.minusDays(dayOfMonth - 1);
        // 获取当前是周几，返回值为1-7    1表示周一 7表示周日
        DayOfWeek weekday = date.getDayOfWeek();
        int weekdayValue = weekday.getValue();
        // 获取月份
        int curMonth = date.getMonthValue();

        // 打印月历表头
        System.out.println("周一  周二  周三  周四  周五  周六  周日");
        // 打印月历首行缩进
        for (int i = 0; i < weekdayValue; i++) {
            System.out.print("    ");
        }
        //
        int month = curMonth;
        while(month == curMonth) {
            int dayValue = date.getDayOfMonth();
            boolean isToday = dayValue == dayOfMonth;
            boolean isWeekend = date.getDayOfWeek().getValue() == 7;
            String dayStr = (dayValue > 9 ? " " : "  ") + dayValue + (isToday ? "* " : "  ");
            System.out.print(dayStr);
            if (isWeekend) {
                System.out.println();
            }
            date = date.plusDays(1);
            month = date.getMonthValue();
        }
    }
}