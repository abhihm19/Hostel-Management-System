import axios from 'axios';

const BASE_URL = "http://localHost:7777/admin";

class AdminService {

    updateAdmin(adminDto){
        return axios.put(BASE_URL, adminDto);
    }

    viewAdmin(){
        return axios.get(BASE_URL);
    }    
}

export default new AdminService();