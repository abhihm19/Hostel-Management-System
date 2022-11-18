import axios from 'axios';

const ADMIN_BASE_URL = "http://localhost:7777/admin/";
class ConcernService {

    getConcerns(){
        return axios.get(ADMIN_BASE_URL + "concerns/display");
    }
}

export default new ConcernService();