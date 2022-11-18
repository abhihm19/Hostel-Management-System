import axios from 'axios';

const API_BASE_URL = "http://localHost:7777/admin/hostel/";

axios.interceptors.request.use(config => {
  let token1 = sessionStorage.getItem("jwt")

  if (token1) {
    const token = 'Bearer ' + token1;
    config.headers.Authorization = token;
  }

  return config;
});

class HostelService {

  getHostelList() {
    return axios.get(API_BASE_URL + "list");

  }

  addHostel(hostelDto) {
    return axios.post(API_BASE_URL, hostelDto);
  }

  updateHostel(hostelDto) {
    return axios.put(API_BASE_URL, hostelDto);
  }

  deleteHostel(hostelDto) {
    return axios.delete(API_BASE_URL, hostelDto);
  }

}

export default new HostelService();
