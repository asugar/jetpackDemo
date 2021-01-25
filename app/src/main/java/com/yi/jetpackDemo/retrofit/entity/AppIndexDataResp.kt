package com.yi.jetpackDemo.retrofit.entity

import android.text.TextUtils
import java.io.Serializable

class AppIndexDataResp {

    var healthData: HealthDataBean? = null
        get() = if (field == null) {
            HealthDataBean()
        } else {
            field
        }

    var sjkView: Int = 0
    var ssgkData: SsgkDataBean? = null
    var noCompleteNumber: Int = 0
    var refactorTaskNotCompleteNum = 0
    var ssgkView: Int = 0
    var sjkData: SjkDataBean? = null
    var jsglData: JsglDataBean? = null
    var weekEndTime: Long = 0
    var weekBeginTime: Long = 0

    var boutiqueActivityList: ArrayList<HomeActivityList>? = null

    var bxjyUrl = ""

    val planInfo: PlanInfoResp? = null
    var isRoleView: Int = 0 // 账号助理: 0不显示；1显示

    var isNewOrderView: Int = 0 // V3.1.0添加 -2:不显示菜单，-1:显示new标签，0:不显示，非0和-1显示数字

    var renewalTips: RenewalTipsResp? = null// V3.1.0添加 续签提示信息

    data class RenewalTipsResp(
        var content: String = "",// 提示内容
        var colourFont: String = ""// 变色字体
    )

    data class PlanInfoResp(
        var logoType: Int = 0,//0:不显示，1:活动开始前 （9.14-9.30）2:活动进行中（10.1-12.31）3:活动已结束（1.1-1.31）
        var isJoin: Int = 0//是否已经参与报名（1、活动介绍报名页面；2、活动筹备页；3：数据页，4：默认结束页，5：活动结果页）
    )

    class HealthDataBean : Serializable {
        /**
         * insId : 2838
         * startScore : 67.0
         * startDesc : 及格
         * startRank : 23
         * startRate : 23.0
         * period : 201932
         */

        var insId: Int = 0
        var startScore: Int = 0
        var startDesc: String? = null
        var startRank: Int = 0
        var startRate: Double = 0.toDouble()
        var period: Int = 0
    }

    class SsgkDataBean : Serializable {
        /**
         * ssZwggtjRate : 100.0
         * ssWjkkcNum : 2
         * ssZwggDzRate : 15.79
         */

        var ssZwggtjRate: String = "0"
        var ssWjkkcNum: Int = 0
        var ssZwggDzRate: String = "0"
            get() {
                return if (TextUtils.isEmpty(field)) {
                    "0"
                } else {
                    field
                }
            }
    }

    class HomeActivityList {
        var boutiqueId = 0 //id
        var title = ""
        var imgUrl = ""
        var jumpType = 1 //跳转类型（1：普通web页面，2：行事历活动）',
        var jumpContent = "" //跳转地址或者活动id
        var contentType = 1 //1:图文，2:图文+直播，3:图文+录播
        var activityClassType = 0
    }

    class SjkDataBean : Serializable {
        /**
         * sjkTjRate : 100
         * sjkDzRate : 0.9
         * sjkZtRate : 100
         * sjkJhRate : 45.6
         */

        var sjkTjRate: String? = null
        var sjkDzRate: String? = null
        var sjkZtRate: String? = null
        var sjkJhRate: String? = null
    }

    class JsglDataBean : Serializable {
        /**
         * insId : 2838
         * valid : 1
         * subjectProdectId : 22
         * subjectProdectName : 小学数学
         * standardedNumber : 32
         * standardNumber : 23
         * dateType : 1
         * datetype : 1
         * period : 201932
         */

        var insId: Int = 0
        var valid: Int = 0
        var subjectProdectId: Int = 0
        var subjectProdectName: String? = null
        var downStandardNum: Int = 0
        var standardNum: Int = 0
        var dataType: Int = 0
        var datetype: Int = 0
        var period: Int = 0
    }

    override fun toString(): String {
        return "AppIndexDataResp(sjkView=$sjkView, ssgkData=$ssgkData, noCompleteNumber=$noCompleteNumber, refactorTaskNotCompleteNum=$refactorTaskNotCompleteNum, ssgkView=$ssgkView, sjkData=$sjkData, jsglData=$jsglData, weekEndTime=$weekEndTime, weekBeginTime=$weekBeginTime, boutiqueActivityList=$boutiqueActivityList, bxjyUrl='$bxjyUrl', planInfo=$planInfo, isRoleView=$isRoleView, isNewOrderView=$isNewOrderView, renewalTips=$renewalTips)"
    }


}