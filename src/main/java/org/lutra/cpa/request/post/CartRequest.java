package org.lutra.cpa.request.post;

/**
 * Structure of POST /cart request
 * @see <a href="http://api.yandex.ru/market/partner/doc/dg/reference/post-cart.xml">reference</a>
 */
public class CartRequest
{
    Cart cart;
    public Cart uw(){return cart;}
}
