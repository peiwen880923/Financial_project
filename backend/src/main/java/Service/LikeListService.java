package Service;
import Entity.LikeList;
import Repository.LikeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeListService {
    @Autowired
    private LikeListRepository likeListRepository;

    public LikeList addLikeList(LikeList likeList) {

        return likeListRepository.save(likeList);
    }
    public List<LikeList> getAllLikeLists() {

        return likeListRepository.findAll();
    }
    public LikeList getLikeListBySn(Long sn) {

        return likeListRepository.findBySN(sn);
    }
    public LikeList updateLikeList(Long sn, LikeList updatedLikeList) {
        LikeList likeList = likeListRepository.findBySN(sn);
        if (likeList != null) {
            likeList.setOrderName(updatedLikeList.getOrderName());
            likeList.setUser(updatedLikeList.getUser());
            likeList.setTotalFee(updatedLikeList.getTotalFee());
            likeList.setTotalAmount(updatedLikeList.getTotalAmount());
            return likeListRepository.save(likeList);
        }
        return null;
    }

    public boolean deleteLikeList(Long sn) {
        LikeList likeList = likeListRepository.findBySN(sn);
        if (likeList != null) {
            likeListRepository.delete(likeList);
            return true;
        }
        return false;
    }
}
