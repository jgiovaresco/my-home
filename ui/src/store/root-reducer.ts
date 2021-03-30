import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import type { History } from 'history';

import { booksSlice } from '../library';

export const rootReducer = (history: History) =>
  combineReducers({
    router: connectRouter(history),
    library: combineReducers({
      books: booksSlice.reducer,
    }),
  });

export default rootReducer;
