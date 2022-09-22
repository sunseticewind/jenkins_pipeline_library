def call(Source_Code_Folder,Proj_File,Runtime,Output_Folder){
    def filePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
    def folderPathList = filePath.split("/")
    def folderPath = ""
    for(int i=0;i<folderPathList.size()-2;i++){
        folderPath+=folderPathList[i]+"/"
    }
    def compostFilePath=folderPath+
    sh "docker-compose run --user \$(id -u):\$(id -g) -v ${Source_Code_Folder}:/code dotnet_3_compiler 'dotnet build /code/${Proj_File} --configuration release --runtime ${Runtime}'"
    sh "docker-compose run --user \$(id -u):\$(id -g) -v ${Source_Code_Folder}:/code -v ${Output_Folder}:/publish dotnet_3_compiler 'dotnet publish /code/${Proj_File} --configuration release --runtime ${Runtime} --output /publish'"
}