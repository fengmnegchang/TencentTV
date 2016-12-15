/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12上午10:19:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import com.open.tencenttv.bean.UserReplyBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12上午10:19:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserReplyJson extends CommonTJson {
//http://video.coral.qq.com/user/0/msg/v2?msgtype=reply&callback=messages3&msgid=&pageflag=1&reqnum=10&type=3&_=1481509025355
    private String errCode;//0,
    private UserReplyBean data;//
//    "info":Object{...}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public UserReplyBean getData() {
		return data;
	}
	public void setData(UserReplyBean data) {
		this.data = data;
	}
    
	/**
	 * {
    "errCode":0,
    "data":{
        "first":"1481509771.629",
        "last":"1481508938.8248",
        "reqnum":10,
        "retnum":2,
        "total":2,
        "messages":[
            {
                "id":"6213902359623979923",
                "parent":"6213902303055389561",
                "targetid":"1662723895",
                "tipstype":3,
                "tipstime":1481509771.629
            },
            {
                "id":"6213898866371509311",
                "parent":"6213897320984392608",
                "targetid":"1663035439",
                "tipstype":3,
                "tipstime":1481508938.8248
            }
        ],
        "comments":{
            "6213902359623979923":{
                "targetid":1662723895,
                "appid":10099,
                "parent":"6213902303055389561",
                "time":1481509771,
                "userid":493294704,
                "content":"哦哦",
                "up":0,
                "poke":0,
                "repnum":0,
                "checkhotscale":0,
                "checkstatus":0,
                "isdeleted":0,
                "address":"",
                "location":"",
                "richtype":0,
                "rootid":"6213902303055389561",
                "title":"",
                "abstract":"",
                "busstype":"default"
            },
            "6213902303055389561":{
                "targetid":1662723895,
                "appid":10099,
                "parent":"0",
                "time":1481509757,
                "userid":33415391,
                "content":"大胸",
                "up":0,
                "poke":0,
                "repnum":1,
                "checkhotscale":0,
                "checkstatus":0,
                "isdeleted":0,
                "address":"",
                "location":"",
                "richtype":0,
                "rootid":"0",
                "title":"",
                "abstract":"",
                "busstype":"default"
            },
            "6213898866371509311":Object{...},
            "6213897320984392608":Object{...}
        },
        "articles":{
            "1662723895":{
                "appid":10099,
                "targetid":1662723895,
                "articleid":"gq0qcw3o467m4gj",
                "commentnum":755,
                "title":"饭局的诱惑",
                "url":"http://v.qq.com/cover/g/gq0qcw3o467m4gj.html",
                "mediatype":0
            },
            "1663035439":Object{...}
        },
        "usermeta":{
            "userid":33415391,
            "nick":"御守",
            "head":"http://q4.qlogo.cn/g?b=qq&k=wSLCLgsnNYsxT924yLUn3Q&s=40&t=1481472000",
            "gender":1,
            "region":"中国:上海:浦东新区",
            "hwvip":0,
            "hwlevel":0,
            "hwannual":0,
            "viptype":0,
            "thirdlogin":0
        },
        "users":{
            "33415391":{
                "userid":"33415391",
                "nick":"御守",
                "head":"http://q4.qlogo.cn/g?b=qq&k=wSLCLgsnNYsxT924yLUn3Q&s=40&t=1481472000"
            },
            "493294704":{
                "userid":"493294704",
                "nick":"冯鸣轩",
                "head":"http://q2.qlogo.cn/g?b=qq&k=OqI39tKgJWcXkibLqsGeB9Q&s=40&t=1481472000"
            }
        }
    },
    "info":{
        "time":1481509802
    }
}
	 */
    
}
