package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.MessageModel
import com.sagarika.features.presentation.model.Message

class MessageMapper {


    fun mapMessageModelToMessage(messageModel: MessageModel): Message {
        with(messageModel) {
            return Message(
                affenpinscher,
                african,
                airedale,
                akita,
                appenzeller,
                australian,
                bakharwal,
                basenji,
                beagle,
                bluetick,
                borzoi,
                bouvier,
                boxer,
                brabancon,
                briard,
                buhund,
                bulldog,
                bullterrier,
                cattledog,
                cavapoo,
                chihuahua,
                chippiparai,
                chow,
                clumber,
                cockapoo,
                collie,
                coonhound,
                corgi,
                cotondetulear,
                dachshund,
                dalmatian,
                dane,
                deerhound,
                dhole,
                dingo,
                doberman,
                elkhound,
                entlebucher,
                eskimo,
                finnish,
                frise,
                gaddi,
                germanshepherd,
                greyhound,
                groenendael,
                havanese,
                hound,
                husky,
                keeshond,
                kelpie,
                kombai,
                komondor,
                kuvasz,
                labradoodle,
                labrador,
                leonberg,
                lhasa,
                malamute,
                malinois,
                maltese,
                mastiff,
                mexicanhairless,
                mix,
                mountain,
                mudhol,
                newfoundland,
                otterhound,
                ovcharka,
                papillon,
                pariah,
                pekinese,
                pembroke,
                pinscher,
                pitbull,
                pointer,
                pomeranian,
                poodle,
                pug,
                puggle,
                pyrenees,
                rajapalayam,
                redbone,
                retriever,
                ridgeback,
                rottweiler,
                saluki,
                samoyed,
                schipperke,
                schnauzer,
                segugio,
                setter,
                sharpei,
                sheepdog,
                shiba,
                shihtzu,
                spaniel,
                spitz,
                springer,
                stbernard,
                terrier,
                tervuren,
                vizsla,
                waterdog,
                weimaraner,
                whippet,
                wolfhound
            )
        }
    }

}