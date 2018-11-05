package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService
{

  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {
    Account l_envia = new Account("Enviador",2000);
    Account l_rep = new Account("Andreu",1000);
    new Expectations()
    {{
      am.findAccount("Enviador"); result = l_envia;
      am.findAccount("Andreu"); result = l_rep;
    }};

    as.setAccountManager(am);
    as.transfer("Enviador","Andreu",500);

    new Verifications()
    {{
      am.updateAccount(l_envia);
      am.updateAccount(l_rep);
    }};
  }

}