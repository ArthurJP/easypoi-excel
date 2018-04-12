package com.jp.easypoi.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.jp.easypoi.utils.ExcelUtil;
import com.jp.easypoi.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 张俊鹏 on 4/11/2018
 */
@Controller
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger( PersonController.class );

    @RequestMapping("export")
    public void export(HttpServletResponse response) {

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person( "路飞", "1", new Date() );
        Person person2 = new Person( "娜美", "2", new Date() );
        Person person3 = new Person( "索隆", "1", new Date() );
        Person person4 = new Person( "小狸猫", "1", new Date() );
        personList.add( person1 );
        personList.add( person2 );
        personList.add( person3 );
        personList.add( person4 );

        //导出操作
        ExcelUtil.exportExcel( personList, "花名册", "草帽一伙", Person.class, "title.xls", response );
    }

    @RequestMapping("importExcel")
    public void importExcel() throws Exception {
        String filePath = "F:\\title.xls";
        //解析excel，slf4g
        List<Person> personList = ExcelUtil.importExcel( filePath, 1, 1, Person.class );
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println( "导入数据一共【" + personList.size() + "】行" );

        //TODO 保存数据库
    }

    @RequestMapping("import")
    @ResponseBody
    public List<Person> doImport(@RequestParam("file") MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows( 1 );
        importParams.setTitleRows( 1 );

        // 需要验证
//        importParams.setNeedVerfiy(true);

        List<Person> successList = null;
        try {
            ExcelImportResult<Person> result = ExcelImportUtil.importExcelMore( file.getInputStream(), Person.class,
                    importParams );

            successList = result.getList();
            List<Person> failList = result.getFailList();

            log.info( "是否存在验证未通过的数据:" + result.isVerfiyFail() );
            log.info( "验证通过的数量:" + successList.size() );
            log.info( "验证未通过的数量:" + failList.size() );

            for (Person person : successList) {
                log.info( "成功列表信息:" + person.getName() );
            }
            for (Person person : failList) {
                log.info( "失败列表信息:" + person.getName() );
            }
        } catch (IOException e) {
            log.error( e.getMessage(), e );
        } catch (Exception e) {
            log.error( e.getMessage(), e );
        }
        return successList;
    }
}
