<div class="modal-header">
    <button type="button" class="close" ng-click="cancel()">×</button>
    <h4 class="modal-title">{{item.${table.primaryKey.fieldName}?"编辑":"新增"}}</h4>
</div>
<div class="modal-body container-fluid form-horizontal">
    <form name="form" novalidate>
<#if columns??>
    <#list columns as column>
        <#if column.columnKey != "PRI" && column.editShow>
            <div class="form-group">
                <label class="col-xs-3 control-label <#if column.isNullable == "NO">required</#if>">${column.columnComment}</label>
                <div class="col-xs-7">
                <#if column.isDate>
                    <input ng-lay-date type="text" class="form-control laydateimg"  date-type="date" ng-model="item.${column.fieldName}" <#if column.isNullable == "NO">required</#if>/>
                <#else>
                    <input class="form-control" ng-model="item.${column.fieldName}" type="text" <#if column.isNullable == "NO">required</#if>/>
                </#if>
                </div>
            </div>
        </#if>
    </#list>
</#if>
    </form>
</div>
<div class="modal-footer">
    <button class="btn btn-primary saveParkSteward" ng-click="save(form)">保存</button>
    <button class="btn btn-default" ng-click="cancel()">取消</button>
</div>