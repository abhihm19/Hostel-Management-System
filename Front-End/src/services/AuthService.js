import axios from "axios";

const BASE_URL = "http://localHost:7777";

axios.interceptors.request.use(config => {
  let jwtToken = sessionStorage.getItem("jwt")

  if (jwtToken) {
    const token = 'Bearer ' + jwtToken;
    config.headers.Authorization = token;
  }

  return config;
});

class AuthService {

  signin(loginRequest) {
    return axios.post(BASE_URL + "/signin", loginRequest);
  }

  signup(signUpRequest) {
    return axios.post(BASE_URL + "/signup", signUpRequest);
  }

  getuser() {
    return axios.get(BASE_URL);
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}

export default new AuthService();