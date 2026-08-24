package orvexretail.clothing.orvexstylekey.data.model

import androidx.annotation.StringRes
import orvexretail.clothing.orvexstylekey.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    DRESSES(R.string.zlfjr_category_dresses),
    OUTERWEAR(R.string.zlfjr_category_outerwear),
    KNITWEAR(R.string.zlfjr_category_knitwear),
    ACCESSORIES(R.string.zlfjr_category_accessories),
    FOOTWEAR(R.string.zlfjr_category_footwear)
}
