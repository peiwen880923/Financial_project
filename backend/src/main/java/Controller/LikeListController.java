package Controller;

import Entity.LikeList;
import Service.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likelists")
public class LikeListController {

    @Autowired
    private LikeListService likeListService;

    // 新增
    @PostMapping
    public LikeList addLikeList(@RequestBody LikeList likeList) {
        return likeListService.addLikeList(likeList);
    }

    // 取得所有喜好清單
    @GetMapping
    public List<LikeList> getAllLikeLists() {
        return likeListService.getAllLikeLists();
    }

    // 根據流水序號取得喜好清單
    @GetMapping("/{sn}")
    public ResponseEntity<LikeList> getLikeListBySn(@PathVariable Long sn) {
        LikeList likeList = likeListService.getLikeListBySn(sn);
        if (likeList != null) {
            return ResponseEntity.ok(likeList);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // 更新
    @PutMapping("/{sn}")
    public ResponseEntity<LikeList> updateLikeList(@PathVariable Long sn, @RequestBody LikeList updatedLikeList) {
        LikeList likeList = likeListService.updateLikeList(sn, updatedLikeList);
        if (likeList != null) {
            return ResponseEntity.ok(likeList);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // 刪除
    @DeleteMapping("/{sn}")
    public ResponseEntity<Void> deleteLikeList(@PathVariable Long sn) {
        if (likeListService.deleteLikeList(sn)) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
