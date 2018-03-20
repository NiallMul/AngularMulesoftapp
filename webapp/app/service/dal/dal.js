"use strict";

(function () {

    function Dal ($http, $q, $log) {
    	
        this.http = (function serviceCaller() {
            return {

                GET: function (apiPath) {
                    $log.log("The final path "+apiPath);
                    var deferred = $q.defer();
                    $http.get(apiPath).then(function (result) {
                        deferred.resolve(result.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST: function (apiPath, itemToSave) {
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: apiPath,
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8"
                            },
                            data: JSON.stringify(itemToSave)
                        }
                    ).then(function (results) {
                            deferred.resolve(results.data);
                        }, function (e) {
                            deferred.reject(e);
                        });
                    return deferred.promise;
                },

                PUT: function (apiPath, itemToSave) {
                    var deferred = $q.defer();
                    $log.log("The final path "+apiPath);
                    $log.log("The final item "+JSON.stringify(itemToSave));
                    $http(
                        {
                            method: "put",
                            url: apiPath,
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8"
                            },
                            data: JSON.stringify(itemToSave)
                        }
                    ).then(function (results) {
                    	$log.log("Successfull update"+results)
                            deferred.resolve(results.data);
                        }, function (e) {
                        	$log.log("unsuccessfull update"+e)
                            deferred.reject(e);
                        });
                    return deferred.promise;
                },

                DELETE: function (apiPath, itemToDelete) {
                    var deferred = $q.defer();
                    $http.delete(apiPath + itemToDelete.id).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                }
            }
        })();
        
        $log.debug("DAL Instantiated");
    }
    angular.module("accountApp").service("dal", ["$http", "$q", "$log", Dal]);
}());