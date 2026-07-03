package com.geoquiz.controller.ranking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.stream.Collectors;

import com.geoquiz.utility.LocalFolder;
import com.geoquiz.utility.Pair;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

/**
 * This class is the implementation of the Ranking interface.
 *
 */
//package-protected
final class RankingImpl implements Ranking {
    private static final String PATH = LocalFolder.getLocalFolderPath() + LocalFolder.getFileSeparator() + "ranking.dat";
    private Table<String, Pair<String, String>, Integer> table;
    private final File file = new File(PATH);

    private RankingImpl() { }

    private static final class Holder {
        private static final Ranking INSTANCE = new RankingImpl();
    }

    /**
     * Gets an instance of RankingImpl.
     * @return an instance of RankingImpl
     */
    public static Ranking getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Map<Pair<String, String>, Integer> getPersonalRanking(final String username) throws FileNotFoundException, ClassNotFoundException, IOException {
        this.table = loadTable();
        return this.table.row(username).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<Pair<String, String>, Pair<String, Integer>> getGlobalRanking() throws FileNotFoundException, ClassNotFoundException, IOException {
        this.table = loadTable();
        return this.table.columnMap().entrySet().stream().map(e -> {
            final Map.Entry<String, Integer> maxx = e.getValue().entrySet().stream().max(Map.Entry.comparingByValue()).get();
            return Maps.immutableEntry(e.getKey(), new Pair<>(maxx.getKey(), maxx.getValue()));
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @SuppressWarnings("unchecked")
    private Table<String, Pair<String, String>, Integer> loadTable() throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!isRankingExisting()) {
            return HashBasedTable.create();
        }
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.file)))) {
            return (Table<String, Pair<String, String>, Integer>) in.readObject();
        }
    }

    @Override
    public boolean isRankingExisting() {
        return this.file.exists();
    }

    @Override
    public void save(final String username, final Pair<String, String> mode, final Integer value) throws FileNotFoundException, ClassNotFoundException, IOException {
        if (!this.isRankingExisting()) {
            this.table = HashBasedTable.create();
        } else if (this.table == null) {
            this.table = loadTable();
        }
        if (value > 0 && (this.table.isEmpty() || !this.table.contains(username, mode) || value > this.table.get(username, mode))) {
            this.table.put(username, mode, value);

            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.file)))) {
                out.writeObject(this.table);
            }
        }
    }

    @Override
    public boolean isUserExisting(final String username) {
        return this.table.containsRow(username);
    }
}
