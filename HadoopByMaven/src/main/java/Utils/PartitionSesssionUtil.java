package Utils;

public class PartitionSesssionUtil {
    public static String partitionSesssion(String monthStr) {
        int month = Integer.valueOf(monthStr);
        if (month >= 1 && month <= 3) {
            return "第一季度";
        } else if (month >= 4 && month <= 6) {
            return "第二季度";
        } else if (month >= 7 && month <= 9) {
            return "第三季度";
        } else {
            return "第四季度";
        }
    }
}
