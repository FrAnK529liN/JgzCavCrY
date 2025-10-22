// 代码生成时间: 2025-10-23 06:10:14
package com.example.datadictionary;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.util.List;
import java.util.Map;

// DataDictionaryAction.java
// 使用Struts框架实现数据字典管理

@Namespace("/dataDictionary")
@Action(value = "{"list":"listDataDictionary", "add":"addDataDictionary", "edit":"editDataDictionary", "delete":"deleteDataDictionary"}