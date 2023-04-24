package top.qoj.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@ColumnWidth(25)
public class ExcelUserVO {
    @ExcelProperty(value = "用户名",index = 0)
    private String username;

    @ExcelProperty(value = "密码",index = 1)
    private String password;
}