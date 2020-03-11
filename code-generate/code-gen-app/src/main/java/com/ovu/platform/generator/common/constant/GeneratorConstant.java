package com.ovu.platform.generator.common.constant;

/**
 * @author flpeng
 * @date 2019/8/29 上午11:13
 */
public interface GeneratorConstant {

    /**
     * 生成代码类型
     */
    interface CodeType {
        /**
         * 前台、后台都生成
         */
        String ALL = "all";

        /**
         * 只生成前台代码
         */
        String FRONT = "front";

        /**
         * 只生成后台代码
         */
        String BACK = "back";
    }
}
