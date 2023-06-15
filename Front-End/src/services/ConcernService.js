import axios from 'axios';

const BASE_URL = "http://localhost:7777/concern";

class ConcernService {

    addConcern(concernDto) {
        return axios.post(BASE_URL, concernDto);
    }

    getAllConcernsForSingleUser(){
        return axios.get(BASE_URL + "/get-all-concerns-for-single-user");
    }
    
    getAllConcerns() {
        return axios.get(BASE_URL + "/get-all-concerns");
    }
}

export default new ConcernService();