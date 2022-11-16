import java.math.BigInteger
import java.io.File
import java.security.MessageDigest


val directoryPath = File("./.mgit/objects")

fun String.sha1(): String {
    val md = MessageDigest.getInstance("SHA-1")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun init(){
    if(directoryPath.mkdirs()){
        println("folder created.")
    }else{
        println("folder already exists")
    }
}

fun hashObject(filePath: String, type:String="blob"){

    val file = File(filePath)

        if(!file.exists() || file.isDirectory()){
            println("Not a valid filepath.")
        }else{
            val content = "$type\u0000"+ file.readText()
                val hash = content.sha1();
            println(hash)               
                val obj = File("./.mgit/objects/${hash}")
                obj.writeText(content)
        }
}

fun catFile(OID:String, expected:String="None"){
    val obj = File("./.mgit/objects/${OID}")

        if(!obj.exists() || obj.isDirectory()){
            println("No such object exists.")
        }else{
            val strs = obj.readText().split("\u0000")
            val content = strs[1]
            val type = strs[0]
            if(expected == "None" || expected == type){
                println(content)
            }else{
                println("Object is not of expected type.")
            }
        }
}
fun main(args: Array<String>){
        val command = args[0]
        if(command == "init"){
        }else{
            if(!directoryPath.isDirectory()){
                println("Run mgit init first.")
            }else if(command == "hash-object"){
                hashObject(args[1])
            }else if( command == "cat-file"){
                  if(args.size <= 2) catFile(args[1])
                  else catFile(args[1],args[2])
            }else if( command == "write-tree"){
                writeTree(args[1])
            }else{
                println("Unknown command.")
            }
        }

}
