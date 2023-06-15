import axios from 'axios';

const BASE_URL = "http://localHost:7777/hostel/";

class HostelService {

  addHostel(hostelDto) {
    return axios.post(BASE_URL, hostelDto);
  }

  updateHostel(hostelDto) {
    return axios.put(BASE_URL, hostelDto);
  }

  getAllHostels() {
    return axios.get(BASE_URL + "get-all-hostels");
  }

  deleteHostel(hostelDto) {
    return axios.delete(BASE_URL, hostelDto);
  }
}

export default new HostelService();