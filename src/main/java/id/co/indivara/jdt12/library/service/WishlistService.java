package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Wishlist;
import id.co.indivara.jdt12.library.repo.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    public Wishlist saveWishlist(Wishlist wh){
        Wishlist wishlist = wishlistRepository.save(new Wishlist(wh.getBookId(), wh.getReaderId()));
        return wishlistRepository.save(wishlist);
    }
}
