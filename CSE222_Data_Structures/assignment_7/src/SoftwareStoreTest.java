public class SoftwareStoreTest {
    public static void main(String[] args) {
        SoftwareStore st = new SoftwareStore(new RedBlackTree<>());
        SoftwareStore.Admin admin = st.createAdmin("password");
        admin.add("Adobe Flash 4.0","FREE");
        admin.add("Windows 10", "10$");
        admin.add("Adobe Photoshop 6.0", "120$");
        System.out.println(st.toString());
        SoftwareStore.User user = st.createUser();
        user.searchStore();
        user.searchByName("Windows 10");
        user.buy("Windows 10", "10$");
        user.buy("Norton 5.5", "20₺");
        System.out.println(st.toString());
        admin.delete("Adobe Photoshop 6.0", "120$");
        admin.update("Adobe Flash 4.0", "FREE" ,"1$");
        System.out.println(st.toString());
    }
}
