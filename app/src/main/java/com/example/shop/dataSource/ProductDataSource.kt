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
            Product("Jeju", "150ml", "roślinny żel myjący do twarzy, 150 ml", "19,99zł", gelCategory, "#FFEEE3", R.drawable.image1, initFavourite, initCart),
            Product("Miya", "150ml", "odżywczy żel myjący do twarzy, 150 ml", "22,99zł", gelCategory, "#DEACBA", R.drawable.image2, initFavourite, initCart),
            Product("Tołpa", "140ml", "pielęgnujący żel do mycia i oczyszczania twarzy, 140 ml", "28,99zł", gelCategory, "#8EC7FA", R.drawable.image3, initFavourite, initCart),
            Product("Vianek", "75ml", "białe mydło do twarzy przeciw sebum, 75 ml", "8,99zł", gelCategory, "#E0CAB6", R.drawable.image4, initFavourite, initCart),
            Product("Soraya Plante", "140ml", "naturalny żel do mycia twarzy do każdego typu cery, 150 ml", "15,99zł", gelCategory, "#D5749A", R.drawable.image5, initFavourite, initCart),
            Product("Soraya Kombucha", "150ml", "żel z peelingiem i czerwoną glinką do mycia twarzy, 75 ml", "9,99zł", gelCategory, "#6ACC8A", R.drawable.image6, initFavourite, initCart),
            Product("AA Vegan", "75ml", "żel do mycia twarzy oczyszczająco-seboreguljący, 150 ml", "15,99zł", gelCategory, "#2BA8DC", R.drawable.image7, initFavourite, initCart),
            Product("Uzdrovisko Mak", "50ml", "całodniowy krem do twarzy, 50 ml", "50,99zł", dayCategory, "#D4BFB9", R.drawable.image8, initFavourite, initCart),
            Product("Dermedic Hydrain2", "50ml", "nawilżający krem o przedłużonym działaniu, 50 ml", "39,99zł", dayCategory, "#3965A3", R.drawable.image9, initFavourite, initCart),
            Product("Bielenda Eco Nature", "50ml", "krem nawilżający do twarzy, 50 ml", "20,99zł", dayCategory, "#F9A76E", R.drawable.image10, initFavourite, initCart),
            Product("Revox Just", "30ml", "krem przeciwsłoneczny do twarzy SPF50, 30 ml", "29,99zł", dayCategory, "#CD9D6C", R.drawable.image11, initFavourite, initCart),
            Product("Jumi", "200ml", "wielofunkcyjny żel aloesowy 99%, 200 ml", "24,99zł", dayCategory, "#74BF27", R.drawable.image12, initFavourite, initCart),
            Product("Teatology Matcha Tea", "50ml", "ujędrniający krem do twarzy i szyi, 50 ml", "135,99zł", dayCategory, "#9BC37C", R.drawable.image13, initFavourite, initCart),
            Product("Bielenda Blueberry C-Tox", "40ml", "nawilżająco-rozświetlający krem pianka do twarzy na dzień i na noc, 40 g", "15,99zł", dayCategory, "#AAC9E5", R.drawable.image14, initFavourite, initCart),
            Product("Tołpa Dermo Face Rosacal", "40ml", "regenerujący krem wzmacniający, noc, 40 ml", "38,99zł", nightCategory, "#B28A9B", R.drawable.image15, initFavourite, initCart),
            Product("Miraculum Asta.Plankton", "50ml", "krem-maska do twarzy, 50 ml", "28,99zł", nightCategory, "#F08F3F", R.drawable.image16, initFavourite, initCart),
            Product("Feel Free Odżywianie", "50ml", "naturalny krem do twarzy na noc, 50 ml", "34,99zł", nightCategory, "#EDE3D8", R.drawable.image17, initFavourite, initCart),
            Product("Dermika Misotherapist", "50ml", "krem naprawczy do twarzy na noc, 50 ml", "99,99zł", nightCategory, "#E2C66B", R.drawable.image18, initFavourite, initCart),
            Product("Laq Ekstremalnie Dziki", "500ml", "żel pod prysznic z ekstraktem z dębu, 500 ml", "21,99zł", menCategory, "#BF935A", R.drawable.image19, initFavourite, initCart),
            Product("Laq Doberman Żel", "85ml", "mydło w kostce dla mężczyzn, 85 ml", "12,99", menCategory, "#AC978E", R.drawable.image20, initFavourite, initCart),
            Product("Laq Doberman", "500ml", "żel pod prysznic, 500 ml", "21,99", menCategory, "#DC2C31", R.drawable.image21, initFavourite, initCart),
            Product("Laq Dzik", "85ml", "mydło w kostce dla mężczyzn, 85 ml", "12,99", dayCategory, "#A68764", R.drawable.image22, initFavourite, initCart)
        )

        val shuffledProducts = products.shuffled().toMutableList()
    }

    fun addItem(){
        products.add(Product("", "", "", "", dayCategory, "#FFFFFF", R.drawable.image23, initFavourite, initCart))
        shuffledProducts.add(Product("", "", "", "", dayCategory, "#FFFFFF", R.drawable.image23, initFavourite, initCart))
    }

    fun loadProductsMain(): List<Product> {
        return shuffledProducts
    }

    fun loadProducts(condition: (product: Product) -> Boolean): List<Product> {
        return products.filter { product -> condition(product) }
    }

    fun addProduct(product: Product) {
        products.add(product)
        shuffledProducts.add(product)
    }
}