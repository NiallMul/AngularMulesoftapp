"use strict";

(function () {

    
    function AccountService (accountDal,$log) {

        this.getAccounts = function()
        {
        	return accountDal.getAccounts();
        };
        
        this.deleteAccount = function(jsonInfo)
        {
        	$log.log("DeleteAccountService: "+jsonInfo);
        	return accountDal.deleteAccount(jsonInfo);
        };
        
    }
    
    angular.module("accountApp").service("accountService", ['accountDal','$log', AccountService]);

}());