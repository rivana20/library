package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.entity.Wishlist;
import id.co.indivara.jdt12.library.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@Controller
@RequestMapping("/library")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    Wishlist saveWishlist(Wishlist wishlist){
        return wishlistService.saveWishlist(wishlist);
    }
}
