package com.ovu.platform.generator.common.bo;

import com.ovu.platform.generator.common.constant.GeneratorConstant.CodeType;

import lombok.Data;

public class GenConfig {

    /**
     * 后台项目路径
     */
    private String projectPath;

    /**
     * 后台包路径
     */
    private String backPath;

    /**
     * mapper文件包路径
     */
    private String mapperPath;

    /**
     * 前端项目路径
     */
    private String frontProjectPath;

    /**
     * 前端文件路径
     */
    private String frontPath;

    /**
     * 作者
     */
    private String author;

    /**
     * 生成日期
     */
    private String date;

    /**
     * 是否覆盖
     */
    private Boolean cover = true;

    /**
     * 版本控制名
     */
    private String vcs;

    /**
     * 是否自动commit
     */
    private Boolean commit = true;

    /**
     * 生成代码类型
     *
     * @see com.ovu.platform.generator.common.constant.GeneratorConstant.CodeType
     */
    private String codeType = CodeType.ALL;

    /**
     * 是否去掉表名前缀
     */
    private boolean removeTablePrefix = true;

    /**
     * 后端http前缀
     */
    private String backUrlPrefix;

    /**
     * 前端http前缀
     */
    private String frontUrlPrefix;

    /**
     * 是否需要分页
     */
    private Boolean paging = true;

    /**
     * 模块名称
     */
    private String moduleName;
    
    public String getProjectPath() {
        return projectPath;
    }
    
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
    
    public String getBackPath() {
        return backPath;
    }
    
    public void setBackPath(String backPath) {
        this.backPath = backPath;
    }
    
    public String getMapperPath() {
        return mapperPath;
    }
    
    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }
    
    public String getFrontProjectPath() {
        return frontProjectPath;
    }
    
    public void setFrontProjectPath(String frontProjectPath) {
        this.frontProjectPath = frontProjectPath;
    }
    
    public String getFrontPath() {
        return frontPath;
    }
    
    public void setFrontPath(String frontPath) {
        this.frontPath = frontPath;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public Boolean getCover() {
        return cover;
    }
    
    public void setCover(Boolean cover) {
        this.cover = cover;
    }
    
    public String getVcs() {
        return vcs;
    }
    
    public void setVcs(String vcs) {
        this.vcs = vcs;
    }
    
    public Boolean getCommit() {
        return commit;
    }
    
    public void setCommit(Boolean commit) {
        this.commit = commit;
    }
    
    public String getCodeType() {
        return codeType;
    }
    
    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
    
    public boolean getRemoveTablePrefix() {
        return removeTablePrefix;
    }
    
    public void setRemoveTablePrefix(boolean removeTablePrefix) {
        this.removeTablePrefix = removeTablePrefix;
    }
    
    public String getBackUrlPrefix() {
        return backUrlPrefix;
    }
    
    public void setBackUrlPrefix(String backUrlPrefix) {
        this.backUrlPrefix = backUrlPrefix;
    }
    
    public String getFrontUrlPrefix() {
        return frontUrlPrefix;
    }
    
    public void setFrontUrlPrefix(String frontUrlPrefix) {
        this.frontUrlPrefix = frontUrlPrefix;
    }
    
    public Boolean getPaging() {
        return paging;
    }
    
    public void setPaging(Boolean paging) {
        this.paging = paging;
    }
    
    public String getModuleName() {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
}
