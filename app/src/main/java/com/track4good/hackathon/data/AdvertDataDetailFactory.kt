package com.track4good.hackathon.data

import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.Place
import com.track4good.hackathon.domain.entity.User
import com.track4good.hackathon.domain.entity.UserType

class AdvertDataDetailFactory {
     fun generateMockAdvertDataFactory(id: String): Advert {
        val advertList = arrayListOf<Advert>(
            Advert(
                id = "NFVERHBF243325",
                title = "Fethiye Ev",
                description = "Fethiye · Çocuklu ailelere uygun \n" +
                        " 70Tl Günlük",
                drawableId = com.track4good.hackathon.R.drawable.evv,
                advertOwner = generateAdvertHostOwner1(),
                place = generatePlace1()
            ),
            Advert(
                id = "BNI12BOUDDFJ13412",
                title = "Beşiktaş Ev",
                description = "Beşiktaş · Çocuklu ailelere uygun \n 40Tl Günlük",
                drawableId = com.track4good.hackathon.R.drawable.evv2,
                advertOwner = generateAdvertHostOwner2(),
                place = generatePlace2()
            ),
            Advert(
                id = "LKLOASKMNXJS2351",
                title = "Midyat Ev",
                description = "Midyat · Çocuklu ailelere uygun \n" +
                        "20Tl Günlük",
                drawableId = com.track4good.hackathon.R.drawable.evv3,
                advertOwner = generateAdvertHostOwner3(),
                place = generatePlace3()
            )
        )
        val advertData = advertList.filter {
            it.id == id
        }

        return advertData.first()
    }

    private fun generateAdvertHostOwner1(): User =
        User(
            id = "dlfkmg231",
            type = UserType.Host,
            fullName = "Alparslan Köprülü",
            mail = "alparslan.koprulu@sahibinden.com",
            birthDate = "02/12/1998",
            hesCode = "32FG 341 D",
            userPhoto = com.track4good.hackathon.R.drawable.pp
        )

    private fun generatePlace1(): Place =
        Place(
            id = "324523mkas",
            feature = "Totally free house awaits earthquake victims. There are private rooms and private toilet for guess."
        )

    private fun generateAdvertHostOwner2(): User =
        User(
            id = "AJSDFNNXS5325NH",
            type = UserType.Host,
            fullName = "Ali Yüksel",
            mail = "ali.yuksel@sahibinden.com",
            birthDate = "01/10/1998",
            hesCode = "88FF 141 X",
            userPhoto = com.track4good.hackathon.R.drawable.pp
        )

    private fun generatePlace2(): Place =
        Place(
            id = "320492NJHCDFC",
            feature = "Possibility to stay for earthquake victims."
        )

    private fun generateAdvertHostOwner3(): User =
        User(
            id = "NKJVSDSSSS43",
            type = UserType.Host,
            fullName = "Ecem Yaşar",
            mail = "ecem.yasar@sahibinden.com",
            birthDate = "11/04/1996",
            hesCode = "32FF 336 Y",
            userPhoto = com.track4good.hackathon.R.drawable.evv3
        )

    private fun generatePlace3(): Place =
        Place(
            id = "230492NJHC22C",
            feature = "Possibility to stay for 10 days for earthquake victims."
        )
}