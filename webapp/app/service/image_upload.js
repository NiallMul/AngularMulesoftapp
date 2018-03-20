"use strict";

(function () {

    
    function AccountService (uploadDal,$log) {

        this.upload = function()
        {
        	return accountDal.getAccounts();
        };
        
        this.deleteAccount = function(jsonInfo)
        {
        	$log.log("DeleteAccountService: "+jsonInfo);
        	return accountDal.deleteAccount(jsonInfo);
        };
        
    }
    
    angular.module("accountApp").service("uploadService", ['uploadtDal','$log', UploadService]);

}());