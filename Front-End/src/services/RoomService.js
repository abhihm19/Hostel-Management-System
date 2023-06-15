import axios from 'axios';

const BASE_URL = "http://localHost:7777/room"

class RoomService {
  
    addRoom(roomDto){
        return axios.post(BASE_URL, roomDto);
    }

    updateRoom(roomDto, roomId){
        return axios.put(BASE_URL, roomDto, roomId);
    }

    displayRooms(hostelId){
        return axios.get(BASE_URL + "/", hostelId);
    }
    
    deleteRoomDetail(roomDto){
        return axios.delete(BASE_URL, roomDto);
    }
}

export default new RoomService();