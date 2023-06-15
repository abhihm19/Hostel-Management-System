import axios from 'axios';

const BASE_URL = "http://localHost:7777/payment";

class PaymentService {
  
    makepayment(transactionId) {
        return axios.post(BASE_URL + "/make-payment", transactionId);
    }

    getTransactions() {
        return axios.get(BASE_URL + "/get-all-payments");
    }
}

export default new PaymentService();