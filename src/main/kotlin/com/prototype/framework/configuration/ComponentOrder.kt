package com.prototype.framework.configuration

/**
 * AopやFilterなどの実行順序の管理クラス
 * 実行順序は小さいほど優先度が高くなります
 * [Order]を指定しない、または
 * [Order]でvalueを指定しない場合のデフォルト実行順序はInteger.MAX_VALUEとなります
 */
object ComponentOrder {
    const val SwitchingDataSourceAop = 100
}