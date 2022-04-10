package org.catcat.sereinfish.recall

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.MessageReceipt
import java.util.*

data class MsgData(val time: Long = 0, val source: MessageReceipt<Group>?)

class MsgStack{
    var maxTime = 2 * 60 * 1000
    var msgMap = HashMap<Long, Stack<MsgData>>()
}