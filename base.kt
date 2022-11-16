import java.io.File

fun writeTree(directory:String){
    File(directory).walk().forEach(){
        val path = it.getPath()
        if(!(path.contains(".mgit") || path.contains(".git"))) {
            if(it.isDirectory()){
                println(path)
            }else{
                hashObject(path)
            }
        }  
    }
}

