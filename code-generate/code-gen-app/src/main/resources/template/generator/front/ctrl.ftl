(function () {
    var app = angular.module("angularApp");
    app.controller('${table.changeClassName}Ctrl', function ($scope, $rootScope, $http, $filter, $uibModal, fac) {
        this.$onInit = function () {
            var panelHeight=$(document.body).height()-$('#${table.changeClassName}-top').height()-220;
            $('#${table.changeClassName}-body').css('min-height',panelHeight+'px')
        };
        $scope.search = {};
        $scope.pageModel = {};

        // 初始化
        app.modulePromiss.then(function () {
            fac.initPage($scope, function () {
                $scope.find();
            })
        });

        // 列表
        $scope.find = function (pageNo) {
            $.extend($scope.search, { currentPage: pageNo || $scope.pageModel.currentPage || 1, pageSize: $scope.pageModel.pageSize || 10 });
            $scope.search.pageIndex = $scope.search.currentPage - 1;
            $scope.search.totalCount = $scope.pageModel.totalCount || 0;
            $scope.search.parkId = app.park.parkId;
            fac.getPageResult("${frontUrlPrefix}${backUrlPrefix}/${table.changeClassName}/page", $scope.search, function (data) {
                $scope.pageModel = data;
            });
        };

        // 新增、编辑
        $scope.addOrEdit = function (item) {
            var modal = $uibModal.open({
                animation: true,
                size: 'md',
                templateUrl: '/view/${table.changeClassName?lower_case}/modal.addOrEdit.html',
                controller: '${table.changeClassName}EditCtrl',
                resolve: { item: item }
            });
            modal.result.then(function () {
                $scope.find();
            }, function () {
                $scope.find();
            });
        }

        // 删除
        $scope.delete = function (item) {
            confirm("确定删除吗?", function () {
                $http.get("${frontUrlPrefix}${backUrlPrefix}/${table.changeClassName}/delete/"+item.${table.primaryKey.fieldName}).success(function (resp) {
                    if (resp.code == 0) {
                        msg("删除成功");
                        $scope.find();
                    } else {
                        alert(resp.msg);
                    }
                });
            })
        }
    });

    // 新增or编辑
    app.controller('${table.changeClassName}EditCtrl', function ($scope, $rootScope, $http, $filter, $uibModalInstance, fac, item) {
        // 修改回显
        if(item && item.${table.primaryKey.fieldName}){
            $http.get("${frontUrlPrefix}${backUrlPrefix}/${table.changeClassName}/get/"+item.${table.primaryKey.fieldName}).success(function (resp) {
                $scope.item = resp.data;
            });
        }

        // 保存
        $scope.save = function (form) {
            form.$setSubmitted(true);
            if (!form.$valid) {
                alert('请完成必填项！');
                return false;
            }
            $scope.item.parkId = app.park.parkId;
            $http.post("${frontUrlPrefix}${backUrlPrefix}/${table.changeClassName}/edit", $scope.item).success(function (resp) {
                if (resp.code == 0) {
                    msg("保存成功");
                    $uibModalInstance.close();
                } else {
                    alert(resp.msg);
                }
            });
        }

        //关闭
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    });
})()
