import axios, { AxiosInstance } from 'axios';
import { getToken } from '../auth';

let axiosInstance: AxiosInstance;

export async function getAxiosInstance() {
  const token = await getToken();

  if (!axiosInstance) {
    axiosInstance = axios.create({
      baseURL: 'http://localhost:3000/api',
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  return axiosInstance;
}
