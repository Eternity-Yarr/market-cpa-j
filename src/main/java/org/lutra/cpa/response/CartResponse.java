package org.lutra.cpa.response;

/**
 * Structure of POST /cart response
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class CartResponse
{
    Cart cart;
    public Cart uw(){return cart;}
}
