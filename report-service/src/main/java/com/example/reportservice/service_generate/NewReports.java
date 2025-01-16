package com.example.reportservice.service_generate;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import java.util.HashMap;
import java.util.Map;

public class NewReports {
//    private VirtualizationType virtualizerType;
//
//    public NewReports(VirtualizationType virtualizerType) {
//        this.virtualizerType = virtualizerType;
//    }
//
//    public void generate() throws JRException {
//        //1. Создание параметров для отчетов
//        final Map<String, Object> parameters = new HashMap<>();
//        //2. Создание виртуализатора
//        final JRAbstractLRUVirtualizer virtualizer = createVirtualizer();
//        //3. Если виртуализатор создан, он добавляет в параметры его
//        if (virtualizer != null) {
//            //4. setReadOnly(false) - означает, что виртуализатор может модифицировать данные во время генерации
//            virtualizer.setReadOnly(false);
//            parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
//        }
//
//        //1. Компиляция отчета из шаблона
//
//
//    }

//    private JRAbstractLRUVirtualizer createVirtualizer() {
//        JRAbstractLRUVirtualizer jrAbstractLRUVirtualizer = null;
//        switch (virtualizerType) {
//            case FILE:
//                jrAbstractLRUVirtualizer = new JRFileVirtualizer(500, "virt");
//                break;
//            case SWAP_FILE:
//                final JRSwapFile swapFile = new JRSwapFile("virt", 1024, 1024);
//                jrAbstractLRUVirtualizer = new JRSwapFileVirtualizer(2, swapFile, true);
//                break;
//            case GZIP:
//                jrAbstractLRUVirtualizer = new JRGzipVirtualizer(2);
//                break;
//        }
//        return jrAbstractLRUVirtualizer;
//    }

}
