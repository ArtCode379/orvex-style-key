package orvexretail.clothing.orvexstylekey.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import orvexretail.clothing.orvexstylekey.data.model.Product
import orvexretail.clothing.orvexstylekey.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Sculpted Satin Dress",
            "A fluid occasion dress with a sculpted neckline and bias-cut skirt.",
            ProductCategory.DRESSES, 129.00,
            "https://images.unsplash.com/photo-1566174053879-31528523f8ae?w=1200"
        ),
        Product(
            2, "Ivory City Trench",
            "A modern cotton-blend trench with storm flaps, belt and deep pockets.",
            ProductCategory.OUTERWEAR, 165.00,
            "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?w=1200"
        ),
        Product(
            3, "Plum Merino Cardigan",
            "Fine-gauge merino wool with polished buttons and elongated cuffs.",
            ProductCategory.KNITWEAR, 84.00,
            "https://images.unsplash.com/photo-1576566588028-4147f3842f27?w=1200"
        ),
        Product(
            4, "Arc Leather Shoulder Bag",
            "A compact curved bag with an adjustable strap and cotton-lined interior.",
            ProductCategory.ACCESSORIES, 110.00,
            "https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=1200"
        ),
        Product(
            5, "Elara Suede Loafers",
            "Supple suede loafers with a squared toe and cushioned leather insole.",
            ProductCategory.FOOTWEAR, 98.00,
            "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=1200"
        ),
        Product(
            6, "Nocturne Column Dress",
            "A refined ankle-length silhouette with a boat neck and gentle stretch.",
            ProductCategory.DRESSES, 142.00,
            "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=1200"
        ),
        Product(
            7, "Cropped Boucle Jacket",
            "Textured tailoring with bracelet sleeves and a collarless neckline.",
            ProductCategory.OUTERWEAR, 148.00,
            "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=1200"
        ),
        Product(
            8, "Cloud Rib Sweater",
            "A relaxed wool-blend knit with dropped shoulders and a split hem.",
            ProductCategory.KNITWEAR, 76.00,
            "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=1200"
        ),
        Product(
            9, "Sculptural Gold Hoops",
            "Lightweight statement hoops with an organic brushed finish.",
            ProductCategory.ACCESSORIES, 42.00,
            "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=1200"
        ),
        Product(
            10, "Mira Ankle Boots",
            "Minimal leather boots with a tapered toe and architectural heel.",
            ProductCategory.FOOTWEAR, 135.00,
            "https://images.unsplash.com/photo-1608256246200-53e635b5b65f?w=1200"
        ),
        Product(
            11, "Silk Line Scarf",
            "Pure silk with an abstract plum and teal motif and rolled edges.",
            ProductCategory.ACCESSORIES, 55.00,
            "https://images.unsplash.com/photo-1601924994987-69e26d50dc26?w=1200"
        ),
        Product(
            12, "Willow Wrap Dress",
            "A softly tailored midi wrap dress with a defined waist.",
            ProductCategory.DRESSES, 118.00,
            "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?w=1200"
        )
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
