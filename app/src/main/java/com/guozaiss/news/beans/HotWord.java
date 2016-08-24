package com.guozaiss.news.beans;

import java.util.List;

/**
 * Created by Lenovo on 2016/5/9.
 */
public class HotWord extends JsonResult<HotWord> {

    /**
     * reason : 查询成功
     * result : ["吴宗宪怒呛周杰伦","院士将统一70岁退休","李念顺利产二胎","红点齿蟾","杜淳熊乃瑾同游澳洲","香港我不购物","嘉陵大厦起火","黄山市于亮被查","郭采洁杨佑宁分手","死囚器官移植利益链","成龙接管小龙女","2015年春运落幕","足球改革方案出炉","王府井抢劫","一汽集团董事长被查","廖永远被举报","普京失踪","解放军进驻中缅边境","萨达姆坟墓","黄海波不娶之恩","深圳机场9死22伤车祸","800万现金墙","杭州问题车展","山东有毒汽油","导演李晓自尽","踩红地毯种树","少年四大名捕发布会","徐才厚癌症恶化死亡","北方舰队","化缘校长病逝","克里米亚回家之路","互联网汽车概念股","世界最丑的女人","亚洲基础设施投资银行","央视315晚会"]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<String> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
