package com.shop_online.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.shop_online.common.exception.ServerException;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author ycshang
 * @Date 2023-11-28
 * @Description TODO
 */
public class ExcelUtils {

	/**
	 * 导出excel
	 *
	 * @param response
	 * @param data      输出的数据
	 * @param fileName  输出excel的名称
	 * @param sheetName 输出excel的sheet的名称
	 * @param clazz     输出数据的模板
	 */
	public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) {
//		表头样式
		WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//		设置表头样式居中
		headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//		内容样式
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//		设置内容居中
		contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
		try {
			EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLS).sheet(sheetName).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).registerWriteHandler(horizontalCellStyleStrategy).doWrite(data);

		} catch (Exception e) {
			throw new ServerException("文件导出失败");
		}
	}

	private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		return response.getOutputStream();
	}

}
