/**
 * Class to test FileSystemTree
 */
public class testFileSystemTree {
    public static void main(String[] args) {
        FileSystemTree myFileSystem = new FileSystemTree("root");
        myFileSystem.addDir("root/first_directory");
        myFileSystem.addFile("");
        myFileSystem.addDir("root/second_directory");
        myFileSystem.printFileSystem();
        myFileSystem.addFile("root/first_directory/dir1/file0");
        myFileSystem.printFileSystem();
        myFileSystem.addFile("root/first_directory/dir1/file0/test.txt");
        myFileSystem.remove("root/first_directory");
        myFileSystem.printFileSystem();

    }
}
