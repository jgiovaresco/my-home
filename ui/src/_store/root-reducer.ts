import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import type { History } from 'history';

import { libraryReducer } from '../library/library.reducer';

export const rootReducer = (history: History) =>
  combineReducers({
    router: connectRouter(history),
    library: libraryReducer,
  });

export default rootReducer;
