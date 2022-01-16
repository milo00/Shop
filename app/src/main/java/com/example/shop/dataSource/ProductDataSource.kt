package com.example.shop.dataSource

import com.example.shop.model.Product
import com.example.shop.R

//TODO: connect those categories with the rest of the code
class ProductDataSource {
    companion object {
        private const val initFavourite = false
        private const val menCategory = R.string.dla_mezczyzn
        private const val dayCategory = R.string.krem_na_dzien
        private const val nightCategory = R.string.krem_na_noc
        private const val gelCategory = R.string.zel_do_mycia
        private const val initCart = 0
        val products = mutableListOf(
            Product("Jeju", "150", "roślinny żel myjący do twarzy", "19,99", gelCategory, "#FFEEE3", R.drawable.image1, initFavourite, initCart),
            Product("Miya", "150", "odżywczy żel myjący do twarzy", "22,99", gelCategory, "#DEACBA", R.drawable.image2, initFavourite, initCart),
            Product("Tołpa", "140", "pielęgnujący żel do mycia i oczyszczania twarzy", "28,99", gelCategory, "#8EC7FA", R.drawable.image3, initFavourite, initCart),
            Product("Vianek", "75", "białe mydło do twarzy przeciw sebum", "8,99", gelCategory, "#E0CAB6", R.drawable.image4, initFavourite, initCart),
            Product("Soraya Plante", "140", "naturalny żel do mycia twarzy do każdego typu cery", "15,99", gelCategory, "#D5749A", R.drawable.image5, initFavourite, initCart),
            Product("Soraya Kombucha", "150", "żel z peelingiem i czerwoną glinką do mycia twarzy", "9,99", gelCategory, "#6ACC8A", R.drawable.image6, initFavourite, initCart),
            Product("AA Vegan", "75", "żel do mycia twarzy oczyszczająco-seboreguljący", "15,99", gelCategory, "#2BA8DC", R.drawable.image7, initFavourite, initCart),
            Product("Uzdrovisko Mak", "50", "całodniowy krem do twarzy", "50,99", dayCategory, "#D4BFB9", R.drawable.image8, initFavourite, initCart),
            Product("Dermedic Hydrain2", "50", "nawilżający krem o przedłużonym działaniu", "39,99", dayCategory, "#3965A3", R.drawable.image9, initFavourite, initCart),
            Product("Bielenda Eco Nature", "50", "krem nawilżający do twarzy", "20,99", dayCategory, "#F9A76E", R.drawable.image10, initFavourite, initCart),
            Product("Revox Just", "30", "krem przeciwsłoneczny do twarzy SPF50", "29,99", dayCategory, "#CD9D6C", R.drawable.image11, initFavourite, initCart),
            Product("Jumi", "200", "wielofunkcyjny żel aloesowy 99%", "24,99", dayCategory, "#74BF27", R.drawable.image12, initFavourite, initCart),
            Product("Teatology Matcha Tea", "50", "ujędrniający krem do twarzy i szyi", "135,99", dayCategory, "#9BC37C", R.drawable.image13, initFavourite, initCart),
            Product("Bielenda Blueberry C-Tox", "40", "nawilżająco-rozświetlający krem pianka do twarzy na dzień i na noc", "15,99", dayCategory, "#AAC9E5", R.drawable.image14, initFavourite, initCart),
            Product("Tołpa Dermo Face Rosacal", "40", "regenerujący krem wzmacniający, noc", "38,99", nightCategory, "#B28A9B", R.drawable.image15, initFavourite, initCart),
            Product("Miraculum Asta.Plankton", "50", "krem-maska do twarzy", "28,99", nightCategory, "#F08F3F", R.drawable.image16, initFavourite, initCart),
            Product("Feel Free Odżywianie", "50", "naturalny krem do twarzy na noc", "34,99", nightCategory, "#EDE3D8", R.drawable.image17, initFavourite, initCart),
            Product("Dermika Misotherapist", "50", "krem naprawczy do twarzy na noc", "99,99", nightCategory, "#E2C66B", R.drawable.image18, initFavourite, initCart),
            Product("Laq Ekstremalnie Dziki", "500", "żel pod prysznic z ekstraktem z dębu", "21,99", menCategory, "#BF935A", R.drawable.image19, initFavourite, initCart),
            Product("Laq Doberman Żel", "85", "mydło w kostce dla mężczyzn", "12,99", menCategory, "#AC978E", R.drawable.image20, initFavourite, initCart),
            Product("Laq Doberman", "500", "żel pod prysznic", "21,99", menCategory, "#DC2C31", R.drawable.image21, initFavourite, initCart),
            Product("Laq Dzik", "85", "mydło w kostce dla mężczyzn", "12,99", dayCategory, "#A68764", R.drawable.image22, initFavourite, initCart)
        )

        val shuffledProducts = products.shuffled().toMutableList()
    }

    fun loadProductsMain(): List<Product> {
        return shuffledProducts
    }

    fun loadProducts(condition: (product: Product) -> Boolean): List<Product> {
        return products.filter { product -> condition(product) }
    }

    fun setFavorite(title: String?){
        for (product in products){
            if (product.titleResource == title){
                product.favorite = !product.favorite
            }
        }
    }

    fun setQuantity(title: String?, quantity: Int){
        products.find { it.titleResource == title }?.titleResource = title
        shuffledProducts.find { it.titleResource == title }?.titleResource = title
    }

    fun addProduct(product: Product) {
        products.add(product)
        shuffledProducts.add(product)
    }

    fun deleteProduct(product: Product) {
        products.remove(product)
        shuffledProducts.remove(product)
    }

    fun updateProduct(oldTitle: String?, newTitle: String?, capacity: String?, desc: String?, price: String?,
                      categoryResourceId: Int, colorResource: String?) {
        update(products, oldTitle, newTitle, capacity, desc, price, categoryResourceId, colorResource)
        update(shuffledProducts, oldTitle, newTitle, capacity, desc, price, categoryResourceId, colorResource)
    }

    private fun update(list: List<Product>, oldTitle: String?, newTitle: String?, capacity: String?, desc: String?, price: String?,
                      categoryResourceId: Int, colorResource: String?) {
        list.find { it.titleResource == oldTitle }?.capacityResource = capacity
        list.find { it.titleResource == oldTitle }?.descriptionResource = desc
        list.find { it.titleResource == oldTitle }?.prizeResource = price
        list.find { it.titleResource == oldTitle }?.categoryResourceId = categoryResourceId
        list.find { it.titleResource == oldTitle }?.colorResource = colorResource
        list.find { it.titleResource == oldTitle }?.titleResource = newTitle
    }

    fun getProduct(title: String?): Product? {
        return products.find { it.titleResource == title }
    }
}