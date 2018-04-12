//package com.jp.easypoi.controller;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import com.jp.easypoi.vo.PropertyOwner;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * Created by 张俊鹏 on 4/11/2018
// */
//
//@RequestMapping("/download")
//public class DownloadController {
//    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
//        List<PropertyOwner> list = PropertyOwner.findAll();
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), PropertyOwner.class, list);
//        workbook.write(response.getOutputStream());
//    }
//}
