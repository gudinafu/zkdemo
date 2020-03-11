<div id="angularId" ng-controller="${table.changeClassName}Ctrl">
    <div class="x_panel" id="${table.changeClassName}-top">
        <div class="form-inline">
<#if table.hasQuery>
            <div class="condition">
                <label>筛选条件</label>
                <button class="btn btn-primary pull-right" ng-click="find(1)"><i class="fa fa-search "
                                                                                 style="margin-right: 10px" aria-hidden="true"></i>查询</button>
                <button class="btn btn-default pull-right" ng-click="search={}">重置</button>
            </div>
    <#list table.queryFieldList as column>
            <div class="form-group">
                <label>${column.columnComment}</label>
                <input ng-model="search.${column.fieldName}" type="text" class="form-control" />
            </div>
    </#list>
        </div>
</#if>
    </div>
    <div class="panel panel-default" id="${table.changeClassName}-body" >
        <div class="panel-heading">
            ${userConfig.moduleName}
            <button class="btn btn-primary pull-right" ng-click="addOrEdit()">新增</button>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover" style="margin-top: 10px;">
                <thead class="title">
                <tr>
                    <th style="width: 50px">No</th>
<#if columns??>
    <#list columns as column>
        <#if column.columnShow>
                    <th>${column.columnComment}</th>
        </#if>
    </#list>
                    <th>操作</th>
</#if>
                </tr>
                </thead>
                <tbody>
                <tr ng-if="pageModel.data.length == 0">
                    <td colspan="100" class="text-center" style="text-align: center;">暂无记录</td>
                </tr>
                <tr style="color:#333333;" ng-repeat="item in pageModel.data">
                    <td>{{$index + 1+pageModel.pageSize*pageModel.pageIndex}}</td>
<#if columns??>
    <#list columns as column>
        <#if column.columnShow>
                    <td>{{item.${column.fieldName}||'--'}}</td>
        </#if>
    </#list>
</#if>
                    <td>
                        <a href='javascript:void(0)' class='btn btn-xs' ng-click='addOrEdit(item)'>编辑</a>
                        <a href='javascript:void(0)' class='btn btn-xs' ng-click='delete(item)'>删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="page-footer clearfix">
                <ng-include src="'/common/pager.html'" class="ng-scope" ng-if="pageModel.data.length != 0"></ng-include>
            </div>
        </div>
    </div>

</div>