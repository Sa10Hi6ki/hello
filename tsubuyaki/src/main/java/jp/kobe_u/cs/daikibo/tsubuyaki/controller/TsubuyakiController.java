package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;

@Controller  
public class TsubuyakiController {  
    @Autowired
    TsubuyakiService ts;
    //タイトル画面を表示
    @GetMapping("/")
    String showIndex() {
        return "index";
    }
    //メイン画面を表示
    @GetMapping("/read")
    String showTsubuyakiList(Model model, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "keyname", required = false) String keyname) {
        //全つぶやきを取得
        List<Tsubuyaki> list = ts.getAllTsubuyaki(); 
        model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());  //空フォームをセット
        return "tsubuyaki_list"; //リスト画面を返す
    }
     // 検索結果のページに遷移するエンドポイント
     @GetMapping("/search_result")
     public String showSearchResult(Model model, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "keyname", required = false) String keyname) {
         List<Tsubuyaki> list;
         if (keyword != null && !keyword.isEmpty()) {
             // キーワードでコメントを検索
             list = ts.searchTsubuyaki(keyword);
         } else if (keyname != null && !keyname.isEmpty()) {
             // キーワードで名前を検索
             list = ts.searchName(keyname);
         } else {
             // 全つぶやきを取得
             list = ts.getAllTsubuyaki();
         }
 
         model.addAttribute("tsubuyakiList", list);
         return "search_result"; // 検索結果ページを返す
     }
    //つぶやきを投稿
    @PostMapping("/read")
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {
        //フォームからエンティティに移し替え
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());
        //サービスに投稿処理を依頼
        ts.postTsubuyaki(t);
        return "redirect:/read"; //メイン画面に転送
    }
}
