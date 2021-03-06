import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;


public class CheckoutTest2 {

    static BasketFromConfig basket;
    static HashMap<String, Item> config;

    @BeforeClass
    public static void beforeAllTestMethods() {

        CatalogFromConfig loadCatalog = new CatalogFromConfig();
        config = loadCatalog.getListOfItems("catalog2.conf");

    }

    @Before
    public void beforeEachMethod() {

        basket = new BasketFromConfig(config);

    }

    @Test
    public void test_totals(){
        Assert.assertEquals(0, basket.price(""),0.0d);

        Assert.assertEquals(50, basket.price("A"),0.0d);
        Assert.assertEquals(80, basket.price("AB"),0.0d);
        Assert.assertEquals(115, basket.price("CDBA"),0.0d);

        Assert.assertEquals(100, basket.price("AA"),0.0d);
        Assert.assertEquals(160, basket.price("AAA"),0.0d);
        Assert.assertEquals(210, basket.price("AAAA"),0.0d);
        Assert.assertEquals(260, basket.price("AAAAA"),0.0d);
        Assert.assertEquals(320, basket.price("AAAAAA"),0.0d);

        Assert.assertEquals(190, basket.price("AAAB"),0.0d);
        Assert.assertEquals(335, basket.price("AAABB"),0.0d);
        Assert.assertEquals(350, basket.price("AAABBD"),0.0d);
        Assert.assertEquals(350, basket.price("DABABA"),0.0d);

    }

    @Test
    public void test_incremental(){
        basket.addItemToBasket("");
        Assert.assertEquals(0,basket.checkTotal(),0.0d);

        basket.addItemToBasket("A");
        Assert.assertEquals(50,basket.checkTotal(),0.0d);

        basket.addItemToBasket("B");
        Assert.assertEquals(80,basket.checkTotal(),0.0d);

        basket.addItemToBasket("A");
        Assert.assertEquals(130,basket.checkTotal(),0.0d);

        basket.addItemToBasket("A");
        //Promotion A 3 x 160
        Assert.assertEquals(190,basket.checkTotal(),0.0d);

        basket.addItemToBasket("B");
        //Promotion A 3 x 160
        //Promotion B 2 x 175
        Assert.assertEquals(335,basket.checkTotal(),0.0d);
    }

}
